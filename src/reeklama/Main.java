package reeklama;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static String passphrase = "And is there honey still for tea?";

    public static void main(String[] args) throws IOException {
        FileWriter middle = new FileWriter("src\\resources\\middle.txt"); //промежуточный файл с зашифрованным текстом файла
        FileWriter file2 = new FileWriter("src\\resources\\out.txt"); //файл с декодированным текстом
        try(FileReader file1 = new FileReader("src\\resources\\in.txt"))
        {
            BufferedReader reader = new BufferedReader(file1);
            // считаем сначала первую строку
            String line = reader.readLine();

            //создание шифра из первых 16ти бит строки(её можно сделать любой, например, считывать у пользователя, сделал как проще для проверки)
            TEA tea = new TEA(passphrase.getBytes());

            while (line != null) {
                byte[] original = line.getBytes();

                byte[] crypt = tea.encrypt(original); //шифруем

                middle.write(Arrays.toString(crypt) + '\n');//записываем зашифрованные данные
                middle.flush();

                byte[] result = tea.decrypt(crypt);//дешифруем

                file2.write((new String(result)) + '\n'); //записываем расшифрованные данные
                file2.flush();

                line = reader.readLine();// считываем остальные строки в цикле
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
