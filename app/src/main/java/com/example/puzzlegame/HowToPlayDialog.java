package com.example.puzzlegame;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Objects;

public class HowToPlayDialog extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle("how to play?");
        builder.setMessage("" +
                "1) First chose the size of the board\n" +
                "2) There is no need to shuffle… we will do it for you.\n" +
                "3) You are ready. Play and injoy. Your goal is to move the puzzle parts to their place: the sequence you dicided to solve\n" +
                "\ntip form us: if you are unhappy with the starting position, or made a mistake you can just shuffle again and start from the beginning.\n" +
                "\nAnd remember not everything is possible.");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }

}
