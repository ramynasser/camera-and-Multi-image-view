package com.example.ramy.camera;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {



    private static final int CAMERA_REQUEST = 1;
    private static final int GALLERY_REQUEST = 2;
    Button take_one ,take_two,take_three,take_four;
    ImageView image_one,image_two,image_three,image_four;
    ImageView image ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        take_one= (Button) findViewById(R.id.takebutton_one);
        take_two= (Button) findViewById(R.id.takebutton_two);
        take_three= (Button) findViewById(R.id.takebutton_three);
        take_four= (Button) findViewById(R.id.takebutton_four);


        image_one= (ImageView) findViewById(R.id.imageView_one);
        image_two= (ImageView) findViewById(R.id.imageView_two);
        image_three= (ImageView) findViewById(R.id.imageView_three);
        image_four= (ImageView) findViewById(R.id.imageView_four);


        take_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  image=image_one;
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                build.setItems(R.array.camera_choices, mDialogListener);
                AlertDialog dialog = build.create();
                dialog.show();
            }
        });

        take_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image=image_two;


                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                build.setItems(R.array.camera_choices, mDialogListener);
                AlertDialog dialog = build.create();
                dialog.show();
            }
        });

        take_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image=image_three;
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                build.setItems(R.array.camera_choices, mDialogListener);
                AlertDialog dialog = build.create();
                dialog.show();
            }
        });

        take_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image=image_four;
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                build.setItems(R.array.camera_choices, mDialogListener);
                AlertDialog dialog = build.create();
                dialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    protected DialogInterface.OnClickListener mDialogListener =
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    switch (i) {
                        case 0: // Take picture
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);

                            break;

                        case 1: // Choose picture
                            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                            galleryIntent.setType("image/*");

                            startActivityForResult(galleryIntent, GALLERY_REQUEST);
                            break;

                    }
                }

            };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(photo);

        }
        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
