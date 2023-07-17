package algonquin.cst2335.hulf0002.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import algonquin.cst2335.hulf0002.databinding.DetailsLayoutBinding;

public class MessageDetailsFragment extends Fragment {

    DetailsLayoutBinding variableBinding;
    ChatMessage selected;

    public MessageDetailsFragment(ChatMessage m){
        selected = m;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        variableBinding = DetailsLayoutBinding.inflate(inflater, container, false);

        variableBinding.tvMessageDetails.setText(selected.message);
        variableBinding.tvTimeDetails.setText(selected.timeSent);
        variableBinding.tvDataID.setText("ID = " + selected.id);

        return variableBinding.getRoot();

    }
}
