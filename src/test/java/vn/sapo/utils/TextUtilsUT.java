package vn.sapo.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TextUtilsUT {
    @Test
    public void removeNonAsciiCharWithNormalizer(){
        String input = "Đôi khi chúng ta không thích viết unit test.... (*)( mà !!@#4 haha cứ phải viết";
        String ascii = TextUtils.removeAccentWithNormalizer(input);
        assert(ascii.length() > 0);
    }

    @Test
    public void removeSpecialChar(){
        String text = "Đôi khi chúng ta không thích viết unit test.... (*)( mà !!@#4 haha cứ phải viết";
        String ascii = TextUtils.removeAccentWithNormalizer(text);
        String asciiWithoutSpecialCharacter = TextUtils.removeSpecialChar(ascii);
        assert(asciiWithoutSpecialCharacter.length() > 0);
    }
    @Test
    public void removeMultipleSpace(){
        String text = "   Đôi       khi   chúng ta không thích viết unit test.... (*)( mà !!@#4 haha cứ phải viết";
        String output = TextUtils.removeMultipleSpace(text);
        assert(output.length() > 0);
    }
    @Test
    public void standardizeText(){
        String text = "   Đôi       khi   chúng ta không thích viết unit test.... (*)( mà !!@#4 haha cứ phải viết";
        String output = TextUtils.standardizeTextForComparation(text);
        assert(output.length() > 0);
    }
    @Test
    public void textToVector(){
        String text = "   Đôi       khi   chúng ta không thích viết unit test.... (*)( mà !!@#4 haha cứ phải viết";
        double[] vector = TextUtils.textToVector(text);
        assert(vector.length == 36);
    }
    @Test
    public void findClosestTextIndex(){
        String target = "Hồ Chí Minh";
        List<String> comparableTexts = Arrays.asList(
            "Hồ Chí Minh",
            "hà nội",
            "ho chí minh"
        );
        List<Integer> indexes = TextUtils.findClosestTextIndexes(target, comparableTexts);
        assert(indexes.size() == 2);
    }

    @Test
    public void findClosestTextIndexWithStrip(){
        String target = "Thành phố Hồ Chí Minh";
        List<String> comparableTexts = Arrays.asList(
            "Thành phố Hồ Chí Minh",
            "Thành phố hà nội",
            "thành phố ho chí minh",
            "Hồ Chí Minh",
            "oh ihc imnh"
        );
        List<String> prefixes = Arrays.asList(
            "Thành phố",
            "Tỉnh"
        );
        int index = TextUtils.findClosestTextIndex(target, comparableTexts, prefixes, null);
        assert(index > -1);
    }
}
