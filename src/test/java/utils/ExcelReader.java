package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExcelReader {

    public static void generateFeatureFromExcel() throws Exception {

        FileInputStream fis = new FileInputStream("src/test/resources/testdata.xlsx");
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheetAt(0);

        StringBuilder feature = new StringBuilder();

        // 🔥 IMPORTANT: Add @api tag
        feature.append("@api\n");
        feature.append("Feature: Login API\n\n");

        feature.append("  Scenario Outline: Login with multiple users\n");
        feature.append("    Given user sets DemoQA base URI\n");
        feature.append("    When user sends POST request to \"/Account/v1/Login\" with username \"<username>\" and password \"<password>\"\n");
        feature.append("    Then login response should be \"<result>\"\n\n");

        feature.append("    Examples:\n");
        feature.append("      | username | password | result |\n");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            String username = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();
            String result = row.getCell(2).getStringCellValue();

            feature.append("      | ")
                   .append(username).append(" | ")
                   .append(password).append(" | ")
                   .append(result).append(" |\n");
        }

        // 🔥 Write file
        Files.write(
            Paths.get("src/test/resources/features/generated.feature"),
            feature.toString().getBytes()
        );

        wb.close();

        // 🔥 Debug
        System.out.println("Feature file generated successfully!");
    }
}