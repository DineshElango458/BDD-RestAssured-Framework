package pojo;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/*
Pojo class for below schema
{
    "albumId": 1,
    "id": 1,
    "title": "x",
    "url": "https://via.placeholder.com/600/92c952",
    "thumbnailUrl": "https://via.placeholder.com/150/92c952"
  }
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "albumId",
        "id",
        "title",
        "url",
        "thumbnailUrl"
})
public class Pojo1 {


    //@JsonProperty("url")
    

    //@JsonProperty("thumbnailUrl")
    

    //@JsonProperty("thumbnailUrl")
    

    //@JsonAnyGetter

    

    //@JsonAnySetter

  
    
}
