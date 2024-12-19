import android.content.Context
import androidx.lifecycle.*
import com.dicoding.mistoriyy.data.di.Injection
import com.dicoding.mistoriyy.maps.MapsViewModel
import com.dicoding.mistoriyy.storiyy.StoriyLocationRepository


class MapsViewModelFactory private constructor(
    private val storyLocationRepository: StoriyLocationRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapsViewModel::class.java)) {
            return MapsViewModel(storyLocationRepository) as T
        } else {
            throw IllegalArgumentException("Viewmodel class tidak diketahui: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: MapsViewModelFactory? = null
        fun getInstance(
            context: Context
        ): MapsViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MapsViewModelFactory(
                    Injection.storiyLocationRepository(context)
                )
            }.also { instance = it }
    }
}