package uri.dam.socialapp;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class homeVideoFragment extends homeFragment{

    @Override
    Query getQuery() {
        return FirebaseFirestore.getInstance().collection("posts").whereEqualTo("mediaType","video");

    }
}
