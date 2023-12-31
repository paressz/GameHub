package com.farez.projectsbp.data.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.farez.projectsbp.R;
import com.farez.projectsbp.data.model.Game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Game.class}, version = 1)
public abstract class GameDatabase extends RoomDatabase {
    public abstract GameDao gameDao();

    public static volatile GameDatabase INSTANCE;
    public static final ExecutorService dbExecutor = Executors.newSingleThreadExecutor();

    public static GameDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (GameDatabase.class) {
                return INSTANCE = Room
                        .databaseBuilder(context.getApplicationContext(), GameDatabase.class, "games")
                        .addCallback(callback())
                        .build();
            }
        }
        return INSTANCE;
    }
    public static RoomDatabase.Callback callback() {
        return new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                dbExecutor.execute(() -> {
                    for (Game game :Game.sampleGameList()) {
                        INSTANCE.gameDao().insert(game);
                    }
                });
            }
        };
    }
}
