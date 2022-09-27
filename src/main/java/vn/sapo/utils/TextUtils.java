package vn.sapo.utils;

import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.codec.digest.HmacAlgorithms.HMAC_SHA_256;

public class TextUtils {
    public static final String comparableCharSequence = "abcdefghijklmnopqrstuvwxyz0123456789";


    public static int findClosestTextIndex(
        String target,
        List<String> comparableTexts,
        List<String> removablePrefixes,
        List<String> removableSuffixes
    ){
        String lowerTarget = target.toLowerCase();
        List<String> lowerComparableTexts = comparableTexts.stream()
            .map(text -> text.toLowerCase())
            .collect(Collectors.toList());

        if(removablePrefixes != null && removablePrefixes.size() > 0){
            for(String removablePrefix : removablePrefixes){
                lowerTarget = lowerTarget.replaceFirst(removablePrefix.toLowerCase(), "");
            }
            for(int i = 0; i < lowerComparableTexts.size(); i++){
                for(String removablePrefix : removablePrefixes){
                    lowerComparableTexts.set(i, lowerComparableTexts.get(i).replaceFirst(removablePrefix.toLowerCase(), ""));
                }
            }
        }
        if(removableSuffixes != null && removableSuffixes.size() > 0){
            for(String removableSuffix : removableSuffixes){
                lowerTarget = lowerTarget.replaceFirst(removableSuffix.toLowerCase(), "");
            }
            for(int i = 0; i < lowerComparableTexts.size(); i++){
                for(String removableSuffix : removableSuffixes){
                    lowerComparableTexts.set(i, lowerComparableTexts.get(i).replaceFirst(removableSuffix.toLowerCase(), ""));
                }
            }
        }

        int indexCount = 0;
        Integer roundCount = 0;
        List<Integer> resultContainer = new ArrayList<>();

        do{
            int currentRound = roundCount;
            lowerTarget = lowerTarget.substring(0, (lowerTarget.length())*(100 - 10 * currentRound)/100);
            lowerComparableTexts = lowerComparableTexts.stream()
                .map(lowerComparableText -> lowerComparableText.substring(0, (lowerComparableText.length())*(100 - 10 * currentRound)/100))
                .collect(Collectors.toList());

            List<Integer> roundResult = findClosestTextIndexes(lowerTarget, lowerComparableTexts);
            indexCount = roundResult.size();
            resultContainer = roundResult;

            roundCount = roundCount + 1;
        }
        while(indexCount != 1 && roundCount <= 2);

        return resultContainer.get(0);
    }
    public static List<Integer> findClosestTextIndexes(String target, List<String> comparableTexts){
        String standardizedTarget = standardizeTextForComparation(target);
        List<String> standardizedComparableTexts = comparableTexts.stream()
            .map(text -> standardizeTextForComparation(text))
            .collect(Collectors.toList());

        double[] targetVector = textToVector(standardizedTarget);

        Map<Integer, Double> cosins = new HashMap<>();
        for(int i = 0; i < standardizedComparableTexts.size(); i++){
            String standardizedComparableText = standardizedComparableTexts.get(i);

            if(standardizedComparableText.equals(standardizedTarget)){
                cosins.put(i, Double.valueOf(1));
                continue;
            }

            double[] comparabeVector = textToVector(standardizedComparableText);
            double cosin = Math.abs(calculateCosin(targetVector, comparabeVector));

            cosins.put(i, cosin);
        }

        double maxCosin = Collections.max(cosins.values());

        List<Integer> result = new ArrayList<>();
        for(Integer key : cosins.keySet()){
            if(cosins.get(key).equals(maxCosin)){
                result.add(key);
            }
        }
        return result;
    }

    public static double calculateCosin(double[] vector1, double[] vector2){
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            dotProduct += vector1[i] * vector2[i];
            normA += Math.pow(vector1[i], 2);
            normB += Math.pow(vector2[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
    public static double[] textToVector(String input){
        int vectorLength = comparableCharSequence.length();
        double[] vector = new double[vectorLength];
        for(int i = 0; i < vectorLength; i++){
            char ch = comparableCharSequence.charAt(i);
            int count = StringUtils.countMatches(input, ch);
            vector[i] = count;
        }
        return vector;
    }
    public static String standardizeTextForComparation(String input){
        String trimmedLowerText = input.trim().toLowerCase();
        String asciiText = removeAccentWithNormalizer(trimmedLowerText);
        String textWithoutSpecialChar = removeSpecialChar(asciiText);
        String textWithoutMultipleSpecialChar = removeMultipleSpace(textWithoutSpecialChar);
        return textWithoutMultipleSpecialChar;
    }

    public static String removeAccentWithNormalizer(String input){
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String accentRemoved = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        accentRemoved = accentRemoved.replaceAll("Đ", "D");
        accentRemoved = accentRemoved.replaceAll("đ", "d");
        return accentRemoved;
    }
    public static String removeSpecialChar(String input){
        String charSequenceWithoutSpecialCharacter = input.replaceAll("[^a-zA-Z0-9 ]", "");
        return charSequenceWithoutSpecialCharacter;
    }
    public static String removeMultipleSpace(String input){
        String output = input.replaceAll("\\s{2,}", " ").trim();
        return output;
    }
    public static String hmac256(String input, String secret){
        byte[] calculatedHmac = (new HmacUtils(HMAC_SHA_256, secret)).hmac(input);
        String calculatedBase64Hmac = Base64.getEncoder().encodeToString(calculatedHmac);
        return calculatedBase64Hmac;
    }
}
