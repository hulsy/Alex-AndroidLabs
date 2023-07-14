package algonquin.cst2335.hulf0002.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {

        @PrimaryKey (autoGenerate = true)
        @ColumnInfo(name="id")
        public long id;
        @ColumnInfo(name="message")
        String message;
        @ColumnInfo(name="TimeSent")
        String timeSent;
        @ColumnInfo(name="SendOrReceive")
        int sendOrReceive;




        public ChatMessage(String m, String t, int type){
            message = m;
            timeSent = t;
            sendOrReceive = type;
        }

        public ChatMessage(){}

        public String getMessage() {
            return message;
        }

        public String getTimeSent() {
            return timeSent;
        }

        public int getSendOrReceive() {
            return sendOrReceive;
        }
    }
