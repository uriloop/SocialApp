package uri.dam.socialapp;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class homeImagesFragment extends homeFragment{


    @Override
    Query getQuery() {
        return FirebaseFirestore.getInstance().collection("posts").whereEqualTo("mediaType","image");
    }
}
