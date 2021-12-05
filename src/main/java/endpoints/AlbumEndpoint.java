package endpoints;

import io.restassured.response.Response;

public class AlbumEndpoint extends BaseEndpoint{
    private final static String ALBUM_URI = "albums/";

    public Response getAlbum(int id) {
        return given().get(ALBUM_URI + id).then().log().all().extract().response();
    }

    public Response getAllAlbums() {
        return given().get(ALBUM_URI).then().log().all().extract().response();
    }


//    public Response deleteAlbum(int id) {
//        return given().delete(ALBUM_URI + id).then().log().all().extract().response();
//    }
//
//    public Response addNewAlbum(int userID, String title) {
//        var newAlbum = new Album(userID, title);
//        return given().body(newAlbum).post(ALBUM_URI).then().log().all().extract().response();
//    }
//
//    public Response updateAlbum(int id, int userID, String newTitle) {
//        var modifiedAlbum = new Album();
//        modifiedAlbum.setTitle(newTitle);
//        modifiedAlbum.setUserID(userID);
//        return given().body(modifiedAlbum).put(ALBUM_URI + id).then().log().all().extract().response();
//}
}
