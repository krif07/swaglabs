package edit.utils;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DocumentHelper {
    XWPFDocument document;

    public DocumentHelper() {
        document = new XWPFDocument();
    }

    public void createTitle(String title) {
        XWPFParagraph paragraphTitle = document.createParagraph();
        paragraphTitle.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun runTitle = paragraphTitle.createRun();
        runTitle.setText(title);
        runTitle.setBold(true);
        runTitle.setFontSize(16);
        runTitle.setFontFamily("Arial");
        runTitle.setColor("2F5496");
        runTitle.addBreak();
    }
    public void createSubtitle(String subtitle) {
        XWPFParagraph paragraphSubTitle = document.createParagraph();
        paragraphSubTitle.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun subTitleRun = paragraphSubTitle.createRun();
        subTitleRun.setText(subtitle);
        subTitleRun.setItalic(true);
        subTitleRun.setFontSize(12);
        subTitleRun.setFontFamily("Calibri");
        subTitleRun.addBreak();
        subTitleRun.addBreak();
    }
    public void createParagraph(String paragraph) {
        XWPFParagraph finalParagraph = document.createParagraph();
        XWPFRun finalRun = finalParagraph.createRun();
        finalRun.setText(paragraph);
    }
    public void createRedSubTitle(String paragraph) {
        XWPFParagraph subtitle = document.createParagraph();
        XWPFRun finalRun = subtitle.createRun();
        finalRun.setColor("FF0000");
        finalRun.setFontSize(14);
        finalRun.setItalic(true);
        finalRun.setText(paragraph);
    }
    public void createBlueSubTitle(String paragraph) {
        XWPFParagraph subtitle = document.createParagraph();
        XWPFRun finalRun = subtitle.createRun();
        finalRun.setColor("2F5496");
        finalRun.setFontSize(14);
        finalRun.setItalic(true);
        finalRun.setText(paragraph);
    }
    public void addImage(String imagePath) {
        XWPFParagraph imageParagraph = document.createParagraph();
        imageParagraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun imageRun = imageParagraph.createRun();

        try (InputStream imageStream = Files.newInputStream(Paths.get(imagePath))) {
            imageRun.addPicture(
                    imageStream,
                    XWPFDocument.PICTURE_TYPE_PNG,
                    "error",
                    Units.toEMU(500),
                    Units.toEMU(400)
            );
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
            imageRun.setText("[Aquí debería aparecer una imagen]");
            imageRun.setItalic(true);
        }
        imageRun.addBreak();
        imageRun.addBreak();
    }
    public void saveDocument(String documentName) {
        try{
            FileOutputStream salida = new FileOutputStream(documentName + ".docx");
            document.write(salida);
            document.close();
            salida.close();
        } catch (IOException e) {
            System.err.println("Error al crear el documento: " + e.getMessage());
        }
    }
}
