package algonquin.cst2335.hulf0002.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> editString = new MutableLiveData<>("Hello World");

    public MutableLiveData<Boolean> isCoffeeDrinker =  new MutableLiveData<Boolean>();

}
