package uri.dam.socialapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.IOException;


public class NewPostFragment extends Fragment {

    NavController navController;

    Button publishButton;
    EditText postConentEditText;
    public AppViewModel appViewModel;
    public Uri mediaUri;
    public String mediaTipo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_new_post, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);  // <-----------------
        publishButton = view.findViewById(R.id.publishButton);
        postConentEditText = view.findViewById(R.id.postContentEditText);

        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publicar();
            }
        });

        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);



        view.findViewById(R.id.camara_fotos).setOnClickListener(v -> tomarFoto());
        view.findViewById(R.id.camara_video).setOnClickListener(v -> tomarVideo());
        view.findViewById(R.id.grabar_audio).setOnClickListener(v -> grabarAudio());
        view.findViewById(R.id.imagen_galeria).setOnClickListener(v -> seleccionarImagen());
        view.findViewById(R.id.video_galeria).setOnClickListener(v -> seleccionarVideo());
        view.findViewById(R.id.audio_galeria).setOnClickListener(v -> seleccionarAudio());
        appViewModel.mediaSeleccionado.observe(getViewLifecycleOwner(), media -> {
            this.mediaUri = media.uri;
            this.mediaTipo = media.tipo;
            Glide.with(this).load(media.uri).into((ImageView) view.findViewById(R.id.previsualizacion));
        });


    }

    private final ActivityResultLauncher<String> galeria =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                appViewModel.setMediaSeleccionado(uri, mediaTipo);
            });
    private final ActivityResultLauncher<Uri> camaraFotos =
            registerForActivityResult(new ActivityResultContracts.TakePicture(),
                    isSuccess -> {
                        appViewModel.setMediaSeleccionado(mediaUri, "image");
                    });
    private final ActivityResultLauncher<Uri> camaraVideos =
            registerForActivityResult(new ActivityResultContracts.TakeVideo(), isSuccess
                    -> {
                appViewModel.setMediaSeleccionado(mediaUri, "video");
            });
    private final ActivityResultLauncher<Intent> grabadoraAudio =
            registerForActivityResult(new
                    ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    appViewModel.setMediaSeleccionado(result.getData().getData(),
                            "audio");
                }
            });
    private void seleccionarImagen() {
        mediaTipo = "image";
        galeria.launch("image/*");
    }
    private void seleccionarVideo() {
        mediaTipo = "video";
        galeria.launch("video/*");
    }
    private void seleccionarAudio() {
        mediaTipo = "audio";
        galeria.launch("audio/*");
    }
    private void tomarFoto() {
        try {
            mediaUri = FileProvider.getUriForFile(requireContext(),
                    BuildConfig.APPLICATION_ID + ".fileprovider", File.createTempFile("img",
                            ".jpg",
                            requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)));
            camaraFotos.launch(mediaUri);
        } catch (IOException e) {}
    }
    private void tomarVideo() {
        try {
            mediaUri = FileProvider.getUriForFile(requireContext(),
                    BuildConfig.APPLICATION_ID + ".fileprovider", File.createTempFile("vid",
                            ".mp4",
                            requireContext().getExternalFilesDir(Environment.DIRECTORY_MOVIES)));
            camaraVideos.launch(mediaUri);
        } catch (IOException e) {}
    }
    private void grabarAudio() {
        grabadoraAudio.launch(new
                Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION));
    }

    private void publicar() {
        String postContent = postConentEditText.getText().toString();

        if(TextUtils.isEmpty(postContent)){
            postConentEditText.setError("Required");
            return;
        }

        publishButton.setEnabled(false);

        guardarEnFirestore(postContent);
    }
    private void guardarEnFirestore(String postContent) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Post post = new Post(user.getUid(), (user.getDisplayName() != user.getEmail() ? user.getDisplayName() : null), (user.getPhotoUrl().toString() != null ? user.getPhotoUrl().toString() : null), postContent,mediaTipo,mediaUri.toString());

        FirebaseFirestore.getInstance().collection("posts")
                .add(post)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        navController.popBackStack();
                    }
                });
    }
}