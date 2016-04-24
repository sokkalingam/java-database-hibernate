package resources;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

public class ResourceHelper {
	
	public static Response responseOk(Object object) {
		return _build(Response.ok(object));
	}
	
	public static Response responseNoContent() {
		return _build(Response.noContent());
	}
	
	public static Response responseCreated(Object object) {
		return _build(Response.created(URI.create("")).entity(object));
	}
	
	private static Response _build(ResponseBuilder responseBuilder) {
		return responseBuilder.header("Access-Control-Allow-Origin", "*").build();
	}

}
