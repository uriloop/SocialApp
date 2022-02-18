package uri.dam.socialapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uri.dam.socialapp.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    ImageView photoImageView;
    TextView displayNameTextView, emailTextView;
    FragmentProfileBinding binding;

    public ProfileFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        photoImageView = view.findViewById(R.id.photoImageView);
        displayNameTextView = view.findViewById(R.id.displayNameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        binding.editarProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hola= "hola";
            }
        });


        if(user != null){
            displayNameTextView.setText(user.getDisplayName());
            emailTextView.setText(user.getEmail());
        if (user.getPhotoUrl()!=null) Glide.with(requireView()).load(user.getPhotoUrl()).into(photoImageView);
        }
    }
}