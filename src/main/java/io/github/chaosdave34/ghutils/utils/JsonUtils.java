package io.github.chaosdave34.ghutils.utils;

import com.google.gson.Gson;
import io.github.chaosdave34.ghutils.GHUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

public class JsonUtils {
    public static void writeObjectToFile(@NotNull File file, Object object) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(new Gson().toJson(object).getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            GHUtils.PLUGIN.getLogger().warning("Error while writing object to file! " + e.getMessage());
        }
    }

    @Nullable
    public static <T> T readObjectFromFile(@NotNull File file, Class<T> clazz) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();

            return new Gson().fromJson(stringBuilder.toString(), clazz);
        } catch (IOException e) {
            GHUtils.PLUGIN.getLogger().warning("Error while reading object from file! " + e.getMessage());
            return null;
        }
    }
}
