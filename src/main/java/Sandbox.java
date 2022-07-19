import com.google.auth.oauth2.GoogleCredentials;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import static io.restassured.RestAssured.given;


public class Sandbox {

  public static void main(String[] args) throws IOException {

    GoogleCredentials credentials = GoogleCredentials.fromStream
        (new FileInputStream("/Users/vipinbhatt/Downloads/cdf-entcon-e1f554fed76a.json"))
      .createScoped(Arrays.asList("https://www.googleapis.com/auth/firebase.messaging"));
    credentials.refresh();

    String cred =  "Bearer " + credentials.getAccessToken().getTokenValue();
    RestAssured.baseURI = "https://cdf-enterprise-651-latest-cdf-entcon-dot-euw1.datafusion.googleusercontent.com/api";

    // Json Body to pass in the request
    String jsonBody = "{\n" +
      "    \"name\": \"Test_Tags_28june\",\n" +
      "    \"description\": \"Data Pipeline Application\",\n" +
      "    \"artifact\": {\n" +
      "        \"name\": \"cdap-data-pipeline\",\n" +
      "        \"version\": \"6.7.0\",\n" +
      "        \"scope\": \"SYSTEM\"\n" +
      "    },\n" +
      "    \"config\": {\n" +
      "        \"resources\": {\n" +
      "            \"memoryMB\": 2048,\n" +
      "            \"virtualCores\": 1\n" +
      "        },\n" +
      "        \"driverResources\": {\n" +
      "            \"memoryMB\": 2048,\n" +
      "            \"virtualCores\": 1\n" +
      "        },\n" +
      "        \"connections\": [\n" +
      "            {\n" +
      "                \"from\": \"Zendesk\",\n" +
      "                \"to\": \"BigQuery\"\n" +
      "            }\n" +
      "        ],\n" +
      "        \"comments\": [],\n" +
      "        \"postActions\": [],\n" +
      "        \"properties\": {},\n" +
      "        \"processTimingEnabled\": true,\n" +
      "        \"stageLoggingEnabled\": false,\n" +
      "        \"stages\": [\n" +
      "            {\n" +
      "                \"name\": \"Zendesk\",\n" +
      "                \"plugin\": {\n" +
      "                    \"name\": \"Zendesk\",\n" +
      "                    \"type\": \"batchsource\",\n" +
      "                    \"label\": \"Zendesk\",\n" +
      "                    \"artifact\": {\n" +
      "                        \"name\": \"zendesk-plugins\",\n" +
      "                        \"version\": \"1.2.5-SNAPSHOT\",\n" +
      "                        \"scope\": \"USER\"\n" +
      "                    },\n" +
      "                    \"properties\": {\n" +
      "                        \"referenceName\": \"test\",\n" +
      "                        \"adminEmail\": \"vikas.rathee@cloudsufi.com\",\n" +
      "                        \"apiToken\": \"67Wp6CGTSZ5HQP679IDg3NwPihOV1qupDGLBusPD\",\n" +
      "                        \"subdomains\": \"cloudsufi\",\n" +
      "                        \"objectsToPull\": \"Tags\",\n" +
      "                        \"maxRetryCount\": \"20\",\n" +
      "                        \"maxRetryWait\": \"240\",\n" +
      "                        \"maxRetryJitterWait\": \"100\",\n" +
      "                        \"connectTimeout\": \"300\",\n" +
      "                        \"readTimeout\": \"300\",\n" +
      "                        \"zendeskBaseUrl\": \"https://%s.zendesk.com/api/v2/%s\",\n" +
      "                        \"tableNameField\": \"tablename\",\n" +
      "                        \"schema\": \"{\\\"type\\\":\\\"record\\\",\\\"name\\\":\\\"tags\\\",\\\"fields\\\":[{\\\"name\\\":\\\"name\\\",\\\"type\\\":\\\"string\\\"},{\\\"name\\\":\\\"tablename\\\",\\\"type\\\":[\\\"string\\\",\\\"null\\\"]},{\\\"name\\\":\\\"object\\\",\\\"type\\\":\\\"string\\\"},{\\\"name\\\":\\\"count\\\",\\\"type\\\":[\\\"long\\\",\\\"null\\\"]}]}\"\n" +
      "                    }\n" +
      "                },\n" +
      "                \"outputSchema\": [\n" +
      "                    {\n" +
      "                        \"name\": \"etlSchemaBody\",\n" +
      "                        \"schema\": \"{\\\"type\\\":\\\"record\\\",\\\"name\\\":\\\"tags\\\",\\\"fields\\\":[{\\\"name\\\":\\\"name\\\",\\\"type\\\":\\\"string\\\"},{\\\"name\\\":\\\"tablename\\\",\\\"type\\\":[\\\"string\\\",\\\"null\\\"]},{\\\"name\\\":\\\"object\\\",\\\"type\\\":\\\"string\\\"},{\\\"name\\\":\\\"count\\\",\\\"type\\\":[\\\"long\\\",\\\"null\\\"]}]}\"\n" +
      "                    }\n" +
      "                ],\n" +
      "                \"id\": \"Zendesk\",\n" +
      "                \"type\": \"batchsource\",\n" +
      "                \"label\": \"Zendesk\",\n" +
      "                \"icon\": \"fa-plug\"\n" +
      "            },\n" +
      "            {\n" +
      "                \"name\": \"BigQuery\",\n" +
      "                \"plugin\": {\n" +
      "                    \"name\": \"BigQueryTable\",\n" +
      "                    \"type\": \"batchsink\",\n" +
      "                    \"label\": \"BigQuery\",\n" +
      "                    \"artifact\": {\n" +
      "                        \"name\": \"google-cloud\",\n" +
      "                        \"version\": \"0.19.0\",\n" +
      "                        \"scope\": \"SYSTEM\"\n" +
      "                    },\n" +
      "                    \"properties\": {\n" +
      "                        \"referenceName\": \"test\",\n" +
      "                        \"project\": \"auto-detect\",\n" +
      "                        \"dataset\": \"Dataset_28june\",\n" +
      "                        \"table\": \"Tags\",\n" +
      "                        \"serviceAccountType\": \"filePath\",\n" +
      "                        \"serviceFilePath\": \"auto-detect\",\n" +
      "                        \"operation\": \"insert\",\n" +
      "                        \"truncateTable\": \"false\",\n" +
      "                        \"allowSchemaRelaxation\": \"false\",\n" +
      "                        \"location\": \"US\",\n" +
      "                        \"createPartitionedTable\": \"false\",\n" +
      "                        \"partitioningType\": \"TIME\",\n" +
      "                        \"partitionFilterRequired\": \"false\"\n" +
      "                    }\n" +
      "                },\n" +
      "                \"outputSchema\": \"\",\n" +
      "                \"inputSchema\": [\n" +
      "                    {\n" +
      "                        \"name\": \"Zendesk\",\n" +
      "                        \"schema\": \"{\\\"type\\\":\\\"record\\\",\\\"name\\\":\\\"tags\\\",\\\"fields\\\":[{\\\"name\\\":\\\"name\\\",\\\"type\\\":\\\"string\\\"},{\\\"name\\\":\\\"tablename\\\",\\\"type\\\":[\\\"string\\\",\\\"null\\\"]},{\\\"name\\\":\\\"object\\\",\\\"type\\\":\\\"string\\\"},{\\\"name\\\":\\\"count\\\",\\\"type\\\":[\\\"long\\\",\\\"null\\\"]}]}\"\n" +
      "                    }\n" +
      "                ],\n" +
      "                \"id\": \"BigQuery\",\n" +
      "                \"type\": \"batchsink\",\n" +
      "                \"label\": \"BigQuery\",\n" +
      "                \"icon\": \"fa-plug\"\n" +
      "            }\n" +
      "        ],\n" +
      "        \"schedule\": \"0 1 */1 * *\",\n" +
      "        \"engine\": \"spark\",\n" +
      "        \"numOfRecordsPreview\": 100,\n" +
      "        \"rangeRecordsPreview\": {\n" +
      "            \"min\": 1,\n" +
      "            \"max\": \"5000\"\n" +
      "        },\n" +
      "        \"maxConcurrentRuns\": 1\n" +
      "    }\n" +
      "}";

    //method calls
    deployPipeline(cred,jsonBody); // To deploy a pipeline
    startPipeline(cred); // To start a pipeline
    pipelinestatus(cred); //To get the status of the pipeline
    stopPipeline(cred); // To stop a pipeline
      }

  public static void deployPipeline(String cred, String jsonBody){
    Response response1 =  given().header("authorization", cred)
      .accept(ContentType.JSON)
      .contentType(ContentType.JSON)
      .and()
      .body(jsonBody)
      .when()
      .put("/v3/namespaces/Zendesk/apps/Test_Tags_28june-cdap-data-pipeline")
      .then().extract().response();

      ResponseBody responseBody = response1;
      System.out.println("Response:"+responseBody.asString());

  }
  public static void startPipeline(String token) {
    Response response1 =  given().header("authorization", token)
      .accept(ContentType.JSON)
      .contentType(ContentType.JSON)
      .and()
      .when()
      .post("/v3/namespaces/Zendesk/apps/Test_Tags_28june-cdap-data-pipeline/workflows/DataPipelineWorkflow/start")
      .then().extract().response();

    System.out.println("Response:"+response1.statusCode());


  }

  public static void stopPipeline(String token) {
    Response response1 =  given().header("authorization", token)
      .accept(ContentType.JSON)
      .contentType(ContentType.JSON)
      .and()
      .when()
      .post("/v3/namespaces/Zendesk/apps/Test_Tags_28june-cdap-data-pipeline/workflows/DataPipelineWorkflow/stop")
      .then().extract().response();

    System.out.println("Response:"+response1.statusCode());
  }
  public static void pipelinestatus(String token) {

    Response response1 =  given().header("authorization", token)
      .accept(ContentType.JSON)
      .contentType(ContentType.JSON)
      .and()
      .when()
      .get("/v3/namespaces/Zendesk/apps/Test_Tags_28june-cdap-data-pipeline/workflows/DataPipelineWorkflow/status")
      .then().extract().response();

    System.out.println("Response:"+response1.asString());
  }
}


