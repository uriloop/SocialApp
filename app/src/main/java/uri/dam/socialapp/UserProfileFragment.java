package uri.dam.socialapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uri.dam.socialapp.databinding.FragmentUserProfileBinding;


public class UserProfileFragment extends Fragment {

    ImageView userImage;
    TextView userName, userEmail;
    FragmentUserProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding= FragmentUserProfileBinding.inflate(getLayoutInflater());


        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.desar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
       


        /*if(user != null){
            displayNameTextView.setText(user.getDisplayName());
            emailTextView.setText(user.getEmail());
            if (user.getPhotoUrl()!=null) Glide.with(requireView()).load(user.getPhotoUrl()).into(photoImageView);
        }*/


    }
}