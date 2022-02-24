package uri.dam.socialapp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class homeLikesFragment extends homeFragment{

    @Override
    Query getQuery() {

        return FirebaseFirestore.getInstance().collection("posts").limit(50).orderBy("likes", Query.Direction.DESCENDING);
    }
}
