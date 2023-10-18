package edu.project1;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import edu.project1.Exceptions.ErrorPersionJsonString;
import edu.project1.Interfaces.IDictionary;
import edu.project1.Model.WordsObjectModel;
import edu.project1.Utils.GetTextFromFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;
import static edu.project1.Utils.PutTextToFile.putTextToFile;

public class Dictionary implements IDictionary {
    private final WordsObjectModel dictObject;
    private final String textErrorJsonString = "Returned null!";
    private String stringToDictionary = "src/main/resources/dict.json";

    public Dictionary() {
        String json = GetTextFromFile.getTextFromFile(stringToDictionary);
        WordsObjectModel words = new Gson().fromJson(json, WordsObjectModel.class);
        if (words != null) {
            this.dictObject = words;
        } else {
            throw new ErrorPersionJsonString(textErrorJsonString);
        }
    }

    public Dictionary(String path) {
        String json = GetTextFromFile.getTextFromFile(path);
        try {
            WordsObjectModel words = new Gson().fromJson(json, WordsObjectModel.class);
            if (words != null) {
                this.dictObject = words;
            } else {
                throw new ErrorPersionJsonString(textErrorJsonString);
            }
        } catch (JsonSyntaxException e) {
            throw new ErrorPersionJsonString(e.getMessage());
        }
        this.stringToDictionary = path;
    }

    private static String[] removeStringFromArrayByContent(String[] array, String stringToRemove) {
        List<String> filteredList = new ArrayList<>();

        boolean flag = false;
        for (String word : array) {
            if (!word.equals(stringToRemove)) {
                filteredList.add(word);
            } else {
                flag = true;
            }
        }

        return filteredList.toArray(new String[0]);
    }

//    public static void main(String[] args) {
//        var d = new Dictionary();
//        System.out.println(d.getRandomWord());
//        System.out.println(d.addOwnWord("Птица"));
//    }

    public WordsObjectModel getDictionaryObject() {
        return this.dictObject;
    }

    @Override
    public @NotNull String getRandomWord() {
        int randomId = new Random().nextInt(this.dictObject.words.length);
        return this.dictObject.words[randomId];
    }

    @Override
    public boolean addOwnWord(String newWord) {
        boolean containsString = false;
        for (String str : this.dictObject.words) {
            if (str.equals(newWord)) {
                containsString = true;
                break;
            }
        }
        if (!containsString) {

            int currentLength = this.dictObject.words.length;
            String[] combinedArray = Arrays.copyOf(this.dictObject.words, this.dictObject.words.length + 1);
            System.arraycopy(new String[] {newWord}, 0, combinedArray, currentLength, 1);

            var tempArray = this.dictObject;
            tempArray.words = combinedArray;

            String newObjectString = new Gson().toJson(tempArray);
            putTextToFile(newObjectString, this.stringToDictionary);

            this.dictObject.words = combinedArray;

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteWord(String toDeleteWord) {
        String[] filteredWords = removeStringFromArrayByContent(this.dictObject.words, toDeleteWord);

        var tempArray = this.dictObject;
        tempArray.words = filteredWords;

        String newObjectString = new Gson().toJson(tempArray);
        putTextToFile(newObjectString, this.stringToDictionary);

        this.dictObject.words = filteredWords;

    }

    @Override
    public int getCountWords() {
        return this.dictObject.words.length;
    }
}
