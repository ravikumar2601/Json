package apiObjectRepository;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utilsBrowser.configuration.Configuration;
import utilsBrowser.configuration.ConfigurationProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class APIPage {

    public static final Log logger = LogFactory.getLog(APIPage.class);
    public static final String environment = getEnvironment();
    public static final int colNo = 1;

    protected static ConfigurationProvider configurationProvider = new ConfigurationProvider();
    protected Configuration configuration = configurationProvider.getConfiguration(environment);

    private static String getEnvironment() {
        return System.getProperty("environment");
    }

    public String readExcelData(String sheet, int row, int col) throws IOException {
        logger.info(": Read Data from Excel method start");
        File filePath = new File(configuration.getExcelPath());
        FileInputStream stream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(stream);
        Sheet sheetName = workbook.getSheet(sheet);
        String name = null;
        Row rowValue = sheetName.getRow(row);
        Cell cellValue = rowValue.getCell(col);
        CellType type = cellValue.getCellType();
        if (type == CellType.STRING) {
            name = cellValue.getStringCellValue();

        } else if (type == CellType.NUMERIC) {
            double value = cellValue.getNumericCellValue();
            long value1 = (long) value;
            name = String.valueOf(value1);
        }
        return name;
    }


    public List<String> readDatasFromExcel(String sheet, int row, int col) throws IOException {
        logger.info(": readDataFromExcel method start");
        List<String> datas = new ArrayList<>();
        for (int i = 0; i <= 13; i++) {
            String data = readExcelData(sheet, row, col);
            col++;
            datas.add(data);
        }
        return datas;
    }

    public Response vinGetOperation(String sheet, String row) {
        logger.info(": vinGetOperation method start");
        Response resp = null;
        List<String> param = null;
        try {
            int rowNo = Integer.parseInt(row);
            param = readDatasFromExcel(sheet, rowNo, colNo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            RequestSpecBuilder builder = new RequestSpecBuilder();
            assert param != null;
            builder.addQueryParam("vin", param.get(0));
            builder.addQueryParam("countryCode", param.get(2));
            builder.addHeader("ApiKey", param.get(1));
            builder.setBaseUri(configuration.getApiURL());
            RequestSpecification requestSpec = builder.build();
            resp = given().spec(requestSpec).when().get(param.get(3));
            resp.getBody().prettyPrint();
            builder.build().log().all();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public Response hierarchyGetOperation(String sheet, String row, String vin, String make, String model, String year, String oemId) {
        logger.info(":hierarchyGetOperation method start");
        Response resp = null;
        List<String> param = null;
        try {
            int rowNo = Integer.parseInt(row);
            param = readDatasFromExcel(sheet, rowNo, colNo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            RequestSpecBuilder builder = new RequestSpecBuilder();
            assert param != null;
            builder.addQueryParam("yearmakemodel.oemid", oemId);
            builder.addQueryParam("yearmakemodel.year", year);
            builder.addQueryParam("yearmakemodel.make", make);
            builder.addQueryParam("yearmakemodel.model", model);
            builder.addQueryParam("vin", vin);
            builder.addQueryParam("language", param.get(4));
            builder.addHeader("ApiKey", param.get(1));
            builder.setBaseUri(configuration.getApiURL());
            RequestSpecification requestSpec = builder.build();
            resp = given().spec(requestSpec).when().get(param.get(5));
            resp.getBody().prettyPrint();
            builder.build().log().all();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public Response subHierarchyGetOperation(String sheet, String row, String vin, String make, String model, String year, String oemId, String id) {
        logger.info(":subHierarchyGetOperation method start");
        Response resp = null;
        List<String> param = null;
        try {
            int rowNo = Integer.parseInt(row);
            param = readDatasFromExcel(sheet, rowNo, colNo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            RequestSpecBuilder builder = new RequestSpecBuilder();
            assert param != null;
            builder.addQueryParam("yearmakemodel.oemid", oemId);
            builder.addQueryParam("yearmakemodel.year", year);
            builder.addQueryParam("yearmakemodel.make", make);
            builder.addQueryParam("yearmakemodel.model", model);
            builder.addQueryParam("vin", vin);
            builder.addQueryParam("previoushierarchyid", id);
            builder.addQueryParam("language", param.get(4));
            builder.addHeader("ApiKey", param.get(1));
            builder.setBaseUri(configuration.getApiURL());
            RequestSpecification requestSpec = builder.build();
            resp = given().spec(requestSpec).when().get(param.get(5));
            resp.getBody().prettyPrint();
            builder.build().log().all();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public Response imageGetOperation(String sheet, String row, String vin, String make, String model, String year, String oemId, String uri) {
        logger.info(":imageGetOperation method start");
        Response resp = null;
        List<String> param = null;
        try {
            int rowNo = Integer.parseInt(row);
            param = readDatasFromExcel(sheet, rowNo, colNo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            RequestSpecBuilder builder = new RequestSpecBuilder();
            assert param != null;
            builder.addQueryParam("yearmakemodel.oemid", oemId);
            builder.addQueryParam("yearmakemodel.year", year);
            builder.addQueryParam("yearmakemodel.make", make);
            builder.addQueryParam("yearmakemodel.model", model);
            builder.addQueryParam("vin", vin);
            builder.addQueryParam("language", param.get(4));
            builder.addHeader("ApiKey", param.get(1));
            builder.setBaseUri(configuration.getApiURL());
            RequestSpecification requestSpec = builder.build();
            resp = given().spec(requestSpec).when().get(param.get(5) + uri);
            resp.getBody().prettyPrint();
            builder.build().log().all();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public Response bigImageGetOperation(String sheet, String row, String vin, String make, String model, String year, String oemId, String uri, String subId) {
        logger.info(":bigImageGetOperation method start");
        Response resp = null;
        List<String> param = null;
        try {
            int rowNo = Integer.parseInt(row);
            param = readDatasFromExcel(sheet, rowNo, colNo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            RequestSpecBuilder builder = new RequestSpecBuilder();
            assert param != null;
            builder.addQueryParam("yearmakemodel.oemid", oemId);
            builder.addQueryParam("yearmakemodel.year", year);
            builder.addQueryParam("yearmakemodel.make", make);
            builder.addQueryParam("yearmakemodel.model", model);
            builder.addQueryParam("vin", vin);
            builder.addQueryParam("subcategoryId", subId);
            builder.addQueryParam("language", param.get(4));
            builder.addHeader("ApiKey", param.get(1));
            builder.setBaseUri(configuration.getApiURL());
            RequestSpecification requestSpec = builder.build();
            resp = given().spec(requestSpec).when().get(param.get(11) + uri);
            resp.getBody().prettyPrint();
            builder.build().log().all();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public Response attributeGetOperation(String sheet, String row) {
        logger.info(": attributeGetOperation method start");
        Response resp = null;
        List<String> param = null;
        try {
            int rowNo = Integer.parseInt(row);
            param = readDatasFromExcel(sheet, rowNo, colNo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            RequestSpecBuilder builder = new RequestSpecBuilder();
            assert param != null;
            builder.addQueryParam("oemid", param.get(6));
            builder.addQueryParam("year", param.get(7));
            builder.addQueryParam("make", param.get(8));
            builder.addQueryParam("model", param.get(9));
            builder.addQueryParam("language", param.get(4));
            builder.addHeader("ApiKey", param.get(1));
            builder.setBaseUri(configuration.getApiURL());
            RequestSpecification requestSpec = builder.build();
            resp = given().spec(requestSpec).when().get(param.get(10));
            resp.getBody().prettyPrint();
            builder.build().log().all();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public String getId(Response r) {
        logger.info(": getId method start");
        String value = " ";
        String body = r.getBody().asString();
        if (body.length() == 8) {
            value = " ";
        } else {
            List<String> idValue = r.jsonPath().get("Id[0]");
            value = idValue.get(0);
        }
        return value;
    }

    public Response vehicleGetOperation(String sheet, String row) {
        logger.info(": vehicleGetOperation method start");
        Response resp = null;
        List<String> param = null;
        try {
            int rowNo = Integer.parseInt(row);
            param = readDatasFromExcel(sheet, rowNo, colNo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            RequestSpecBuilder builder = new RequestSpecBuilder();
            assert param != null;
            builder.addQueryParam("oemid", param.get(6));
            builder.addQueryParam("year", param.get(7));
            builder.addQueryParam("make", param.get(8));
            builder.addQueryParam("model", param.get(9));
            builder.addHeader("ApiKey", param.get(1));
            builder.setBaseUri(configuration.getApiURL());
            RequestSpecification requestSpec = builder.build();
            resp = given().spec(requestSpec).when().get(param.get(3));
            resp.getBody().prettyPrint();
            builder.build().log().all();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public Response hierarchiesYMMGetOperation(String sheet, String row, String attributeId) {
        logger.info(": HierarchiesYMMGetOperation method start");
        Response resp = null;
        List<String> param = null;
        try {
            int rowNo = Integer.parseInt(row);
            param = readDatasFromExcel(sheet, rowNo, colNo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            RequestSpecBuilder builder = new RequestSpecBuilder();
            assert param != null;
            builder.addQueryParam("yearmakemodel.oemid", param.get(6));
            builder.addQueryParam("yearmakemodel.year", param.get(7));
            builder.addQueryParam("yearmakemodel.make", param.get(8));
            builder.addQueryParam("yearmakemodel.model", param.get(9));
            builder.addQueryParam("attributeids", attributeId);
            builder.addHeader("ApiKey", param.get(1));
            builder.setBaseUri(configuration.getApiURL());
            RequestSpecification requestSpec = builder.build();
            resp = given().spec(requestSpec).when().get(param.get(5));
            resp.getBody().prettyPrint();
            builder.build().log().all();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public Response subHierarchiesYMMGetOperation(String sheet, String row, String previousHierarchyId, String attributeId) {
        logger.info(": subHierarchiesYMMGetOperation method start");
        Response resp = null;
        List<String> param = null;
        try {
            int rowNo = Integer.parseInt(row);
            param = readDatasFromExcel(sheet, rowNo, colNo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            RequestSpecBuilder builder = new RequestSpecBuilder();
            assert param != null;
            builder.addQueryParam("yearmakemodel.oemid", param.get(6));
            builder.addQueryParam("yearmakemodel.year", param.get(7));
            builder.addQueryParam("yearmakemodel.make", param.get(8));
            builder.addQueryParam("yearmakemodel.model", param.get(9));
            builder.addQueryParam("attributeids", attributeId);
            builder.addQueryParam("previoushierarchyid", previousHierarchyId);
            builder.addHeader("ApiKey", param.get(1));
            builder.setBaseUri(configuration.getApiURL());
            RequestSpecification requestSpec = builder.build();
            resp = given().spec(requestSpec).when().get(param.get(5));
            resp.getBody().prettyPrint();
            builder.build().log().all();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public Response searchForAllCategoriesGetOperation(String sheet, String row) {
        logger.info(": searchForAllCategoriesGetOperation method start");
        Response resp = null;
        List<String> param = null;
        try {
            int rowNo = Integer.parseInt(row);
            param = readDatasFromExcel(sheet, rowNo, colNo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            RequestSpecBuilder builder = new RequestSpecBuilder();
            assert param != null;
            builder.addQueryParam("yearmakemodel.oemid", param.get(6));
            builder.addQueryParam("yearmakemodel.year", param.get(7));
            builder.addQueryParam("yearmakemodel.make", param.get(8));
            builder.addQueryParam("yearmakemodel.model", param.get(9));
            builder.addQueryParam("vin", param.get(0));
            builder.addQueryParam("language", param.get(4));
            builder.addQueryParam("q", param.get(13));
            builder.addHeader("ApiKey", param.get(1));
            builder.setBaseUri(configuration.getApiURL());
            RequestSpecification requestSpec = builder.build();
            resp = given().spec(requestSpec).when().get(param.get(12));
            resp.getBody().prettyPrint();
            builder.build().log().all();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }

}