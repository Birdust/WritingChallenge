package com.example.writingchallenge.activities;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migration1to2 extends Migration{
    public Migration1to2() {
        super(1, 2);
    }

    @Override
    public void migrate(SupportSQLiteDatabase database) {
        database.execSQL("ALTER TABLE notes ADD COLUMN new_column TEXT");
    }
}
