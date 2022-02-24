package uri.dam.socialapp;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class homeDateFragment extends homeFragment{


    @Override
    Query getQuery() {
        return FirebaseFirestore.getInstance().collection("posts").limit(50).orderBy("currentTime", Query.Direction.DESCENDING);
    }
}
