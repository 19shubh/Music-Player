package com.example.shubham.music;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {

    private ArrayList<Song> songs;

    public SongAdapter(Context c, ArrayList<Song> theSongs) {
        super(c, 0, theSongs);
    }

    public long getItemId(int arg0) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.songs, parent, false);
        }
        TextView artistView = (TextView) listItemView.findViewById(R.id.song_artist);
        ImageView durationView = (ImageView) listItemView.findViewById(R.id.duration);
        Song currSong = (Song) getItem(position);
        ((TextView) listItemView.findViewById(R.id.song_title)).setText(currSong.getTitle());
        artistView.setText(currSong.getArtist());
   //     durationView.setText(currSong.convertTime(currSong.getDuration()));
        Bitmap albumArt = getAlbumart(currSong.getAlbumId());
        if(albumArt == null){
            durationView.setImageResource(R.drawable.no_album);
        }
        else{
            durationView.setImageBitmap(albumArt);
        }
        //     ((GradientDrawable) durationView.getBackground()).setColor(color);
   //     currSong.setColor(color);
        listItemView.setTag(Integer.valueOf(position));
        return listItemView;
    }

    public Bitmap getAlbumart(Long album_id) {
        Bitmap bm = null;
        try {
            final Uri sArtworkUri = Uri
                    .parse("content://media/external/audio/albumart");

            Uri uri = ContentUris.withAppendedId(sArtworkUri, album_id);

            ParcelFileDescriptor pfd = getContext().getContentResolver()
                    .openFileDescriptor(uri, "r");

            if (pfd != null) {
                FileDescriptor fd = pfd.getFileDescriptor();
                bm = BitmapFactory.decodeFileDescriptor(fd);
            }
        }
        catch (Exception e) {
        }
        return bm;
    }

}
