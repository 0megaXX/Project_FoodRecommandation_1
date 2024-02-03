package com.food1.whateat.data.cached;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(tableName = "app_data")
public class AppData {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private final String id;

    @ColumnInfo(name = "app_date")
    private final LocalDateTime date;

    public AppData(String id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
