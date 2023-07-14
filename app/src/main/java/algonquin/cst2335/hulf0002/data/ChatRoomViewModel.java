package algonquin.cst2335.hulf0002.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ChatRoomViewModel extends ViewModel {
    public ArrayList<ChatMessage> messages = new ArrayList<>();
}
