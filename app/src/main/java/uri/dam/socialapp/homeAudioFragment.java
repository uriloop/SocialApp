package uri.dam.socialapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class homeAudioFragment extends homeFragment{



    @Override
    Query getQuery() {
        return FirebaseFirestore.getInstance().collection("posts").whereEqualTo("mediaType","audio");

    }
}
