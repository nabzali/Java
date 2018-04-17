package pgp;
public class MyFile extends PGPFile{
    public static void main(String[] args)
    {
        MyFile file = new MyFile();
        PGPText x = new PGPText();
        x = file.readEntireFile("filename.txt");
        for (int i = 0; i < 5; i++)
        {
            x.setLine(i, "");
        }
        file.writeEntireFile("filename.txt", x);
    }
    public PGPText readEntireFile(String filename)
    {
        PGPText textobject = new PGPText();
        //PGPFile fileobject = new PGPFile();
        openReadFile(filename);
        String str;
        while ((str = readNextLine())!= null)
        {
            textobject.addLine(str);
        }
        closeReadFile();
        return textobject;
    }
    public void writeEntireFile(String filename, PGPText objectref)
    {
       openWriteFile(filename);
        for (int i = 0; i < objectref.getLineCount(); i++)
        {
            writeLine(objectref.getLine(i));

        }
        closeWriteFile();
    }
}
