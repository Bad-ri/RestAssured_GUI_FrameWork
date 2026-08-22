package Helper.ResponseManager;

import Helper.EnumManager.ApiParameter;
import Helper.ReportManager.ReportManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResponseManager {
    private ApiParameter apiParameter = ApiParameter.PARAMETER;
    ReportManager reportManager = new ReportManager();


    public void parseResponse(String response) {
        if (response != null) {
            String id = JsonPath.from(response).getString("id");
            String description = apiParameter.getDescription();
            apiParameter.setRes(id);
            ReportManager.recordExchange(id, description);
        }else{
            ReportManager.recordExchange("22", "Faaail");
        }
    }
}
