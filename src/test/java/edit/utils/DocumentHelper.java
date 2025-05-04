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
    public static void createDocument(String imagePath) {
        XWPFDocument document = new XWPFDocument();

        try {
            // 1. Crear el título
            XWPFParagraph paragraphTitle = document.createParagraph();
            paragraphTitle.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun runTitle = paragraphTitle.createRun();
            runTitle.setText("DOCUMENTO DE EVIDENCIAS");
            runTitle.setBold(true);
            runTitle.setFontSize(16);
            runTitle.setFontFamily("Arial");
            runTitle.setColor("2F5496");
            runTitle.addBreak();

            // 2. Crear un subtítulo
            XWPFParagraph paragraphSubTitle = document.createParagraph();
            paragraphSubTitle.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun subTitleRun = paragraphSubTitle.createRun();
            subTitleRun.setText("Errores encontrados");
            subTitleRun.setItalic(true);
            subTitleRun.setFontSize(12);
            subTitleRun.setFontFamily("Calibri");
            subTitleRun.addBreak();
            subTitleRun.addBreak();

            // 3. Crear un párrafo normal
            XWPFParagraph normalParagraph = document.createParagraph();
            normalParagraph.setAlignment(ParagraphAlignment.BOTH);

            XWPFRun normalParagraphRun = normalParagraph.createRun();
            normalParagraphRun.setText(
                    "La siguiente es la lista de las pruebas que fallaron por algún motivo. Se adjunta imagen");
            normalParagraphRun.setFontSize(11);
            normalParagraphRun.setFontFamily("Calibri");
            normalParagraphRun.addBreak();
            normalParagraphRun.addBreak();

            // 5. Añadir una imagen
            XWPFParagraph parrafoImagen = document.createParagraph();
            parrafoImagen.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun imageRun = parrafoImagen.createRun();
            //String imagePath = "./test-results/screenshots/" + imagePath;

            try (InputStream imagenStream = Files.newInputStream(Paths.get(imagePath))) {
                imageRun.addPicture(
                        imagenStream,
                        XWPFDocument.PICTURE_TYPE_PNG,
                        "error",
                        Units.toEMU(400),
                        Units.toEMU(300)
                );
            } catch (Exception e) {
                System.out.println("No se pudo cargar la imagen: " + e.getMessage());
                // Si no se puede cargar la imagen, añadimos un texto alternativo
                imageRun.setText("[Aquí debería aparecer una imagen]");
                imageRun.setItalic(true);
            }

            imageRun.addBreak();
            imageRun.addBreak();

            // 6 Añadir un párrafo final
            XWPFParagraph finalParagraph = document.createParagraph();
            XWPFRun finalRun = finalParagraph.createRun();
            finalRun.setText("Fin del documento de ejemplo. Apache POI permite generar documentos DOCX " +
                    "con múltiples formatos y elementos como los que se han mostrado en este ejemplo.");

            // 7 Guardar el documento
            FileOutputStream salida = new FileOutputStream("Evidencias.docx");
            document.write(salida);
            document.close();
            salida.close();

            System.out.println("Documento creado exitosamente.");

        } catch (IOException e) {
            System.err.println("Error al crear el documento: " + e.getMessage());
        }
    }
}
