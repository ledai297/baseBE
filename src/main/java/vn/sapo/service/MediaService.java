package vn.sapo.service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.config.ApplicationProperties;
import vn.sapo.utils.FileUtils;
import vn.sapo.vm.s3amazon.MediaS3DTO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
public class MediaService {
    private final AmazonS3 s3Client;
    private final ApplicationProperties applicationProperties;

    public MediaService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        ApplicationProperties.MediaConfig.AwsConfig awsConfig = applicationProperties.getMedia().getAws();
        AWSCredentials credentials = new BasicAWSCredentials(awsConfig.getAccessKey(), awsConfig.getSecretKey());
        ClientConfiguration config = new ClientConfiguration();
        int timeout = awsConfig.getTimeout();
        config.setConnectionTimeout(timeout);
        config.setSocketTimeout(timeout);
        config.setUseGzip(true);
        config.setRequestTimeout(timeout);
        config.setClientExecutionTimeout(timeout);

        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();
        builder.setCredentials(new AWSStaticCredentialsProvider(credentials));
        builder.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1).getName());
        builder.setPathStyleAccessEnabled(true);
        builder.setClientConfiguration(config);
        this.s3Client = builder.build();
        TransferManagerBuilder transBuilder = TransferManagerBuilder.standard();
        transBuilder.setS3Client(s3Client);
    }

    public String upload(MediaS3DTO mediaS3DTO) throws IOException {
        return uploadToS3(mediaS3DTO);
    }

    private String uploadToS3(MediaS3DTO mediaS3DTO) throws IOException {
        String key;
        if (mediaS3DTO.getBytes().length > 1048576 * 200) {
            throw new IOException("Kích thước file tối đa được upload là 5MB");
        }
        if (mediaS3DTO.getBytes() == null)
            return "";
        key = buildS3Key(mediaS3DTO);
        if (StringUtils.isNotBlank(applicationProperties.getMedia().getPrefix())) {
            key = applicationProperties.getMedia().getPrefix() + "/" + key;
        }

        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentLength(mediaS3DTO.getBytes().length);
        metaData.setContentType(mediaS3DTO.getContentType());
        InputStream inputStream = new ByteArrayInputStream(mediaS3DTO.getBytes());
        ApplicationProperties.MediaConfig.AwsConfig awsConfig = applicationProperties.getMedia().getAws();
        try {
            s3Client.putObject(awsConfig.getBucketName(), key, inputStream, metaData);
        } finally {
            inputStream.close();
        }

        if (mediaS3DTO.isPublicRead()) {
            s3Client.setObjectAcl(awsConfig.getBucketName(), key, CannedAccessControlList.PublicRead);
        }
        String uriPrefix = awsConfig.getBaseUrl() + "/" + awsConfig.getBucketName();
        return uriPrefix + "/" + key;
    }

    private String buildS3Key(MediaS3DTO mediaS3DTO) {
        StringBuilder builder = new StringBuilder();

        if (StringUtils.isNotBlank(mediaS3DTO.getFolderName())) {
            builder.append(mediaS3DTO.getFolderName());
        }
        if (StringUtils.isNotBlank(mediaS3DTO.getFolderName()) && StringUtils.isNotBlank(mediaS3DTO.getFileName())) {
            builder.append("/");
        }
        if (StringUtils.isNotBlank(mediaS3DTO.getFileName())) {
            builder.append(mediaS3DTO.getFileName());
        }

        return builder.toString().replace("//", "/");
    }

    public MediaS3DTO generateMediaS3DTO(byte[] bytes, String filename, String contentType) {
        MediaS3DTO fileUpload = new MediaS3DTO();
        fileUpload.setBytes(bytes);
        fileUpload.setFileName(filename);
        fileUpload.setPublicRead(true);
        fileUpload.setContentType(contentType);
        return fileUpload;
    }

    public String uploadS3Storage(MultipartFile file) throws IOException {
        Date date = new Date();
        String fileName = FilenameUtils.removeExtension(file.getOriginalFilename());
        String standardFileName = FileUtils.getStandardFileName(fileName);
        String outputFileName = standardFileName + "-" + date.getTime();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        MediaS3DTO mediaS3DTO = generateMediaS3DTO(file.getBytes(), outputFileName + "." + ext, file.getContentType());
        String url = upload(mediaS3DTO);
        return url;
    }
}
