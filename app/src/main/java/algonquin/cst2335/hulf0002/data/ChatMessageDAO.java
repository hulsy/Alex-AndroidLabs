package algonquin.cst2335.hulf0002.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ChatMessageDAO {

    @Insert
    public long insertMessage(ChatMessage messageToInsert);

    @Update
    public int updateMessage(ChatMessage updatedMessage);

    @Query("Select * from ChatMessage")
    public List<ChatMessage> getAllMessages();

    @Delete
    public int deleteMessage(ChatMessage messageToDelete);

}
