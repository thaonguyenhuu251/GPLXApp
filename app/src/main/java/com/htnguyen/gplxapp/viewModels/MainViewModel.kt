package com.htnguyen.gplxapp.viewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.htnguyen.gplxapp.base.utils.parseJsonToListTrafficLearn
import com.htnguyen.gplxapp.base.utils.readJSONFromAsset
import com.htnguyen.gplxapp.database.TrafficLearnDatabase
import com.htnguyen.gplxapp.model.StatusLearn
import com.htnguyen.gplxapp.model.TrafficsLearn
import com.htnguyen.gplxapp.repo.TrafficLearnRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TrafficLearnRepo

    var responseTrafficLearn: LiveData<List<TrafficsLearn>>

    var listAdd: ArrayList<TrafficsLearn> = arrayListOf(
        TrafficsLearn(
            0,
            "80 CÂU HỎI ĐIỂM LIỆT",
            "80 câu hỏi điểm liệt",
            "https://www.vba.vic.gov.au/__data/assets/image/0003/103971/warning-sign.png",
            80,
            0,
            listOf()
        ),
        TrafficsLearn(
            1,
            "KHÁI NIỆM VÀ QUY TẮC",
            "Gồm 83 câu hỏi(20 câu điểm liệt)",
            "https://banner2.cleanpng.com/20180422/qge/kisspng-computer-icons-concept-font-concepts-5add2c9c3e81e2.7797215615244443162561.jpg",
            83,
            0,
            listOf()
        ),
        TrafficsLearn(
            2,
            "VĂN HOÁ VÀ ĐẠO ĐỨC LÁI",
            "Gồm 5 câu hỏi",
            "https://banner2.cleanpng.com/20180706/xcx/kisspng-stock-photography-good-ethical-5b3f2b473a9c71.9277581915308665032401.jpg",
            5,
            0,
            listOf()
        ),
        TrafficsLearn(
            3,
            "KĨ THUẬT LÁI XE",
            "Gồm 13 câu hỏi (5 câu điểm liệt)",
            "https://png.pngtree.com/png-vector/20190130/ourlarge/pngtree-simple-green-car-cartoon-material-png-image_603239.jpg",
            13,
            0,
            listOf()
        ),
        TrafficsLearn(
            4,
            "BIỂN BÁO ĐƯỜNG BỘ",
            "Gồm 65 câu hỏi",
            "https://cdn.pixabay.com/photo/2011/04/14/21/05/traffic-sign-6682_960_720.png",
            65,
            0,
            listOf()
        ),
        TrafficsLearn(
            5,
            "SA HÌNH",
            "Gồm 35 câu hỏi",
            "https://banglaixegiare.com/wp-content/uploads/2021/07/sa-hinh-bang-b2-b1-c-2.png",
            35,
            0,
            listOf()
        )


    )

    init {
        val trafficLearnDao = TrafficLearnDatabase.getInstance(application).trafficLearnDao()
        repository = TrafficLearnRepo(trafficLearnDao)
        responseTrafficLearn = repository.getAll()
    }

    fun addData(context: Context) {
        val json = readJSONFromAsset(context, "learn_traffic.json")
        val list = parseJsonToListTrafficLearn(json)
        listAdd.forEachIndexed { index, trafficsLearn ->
            val listfilter = list.filter {
                it.type == index
                        || it.type % 10 == index
                        || (it.type / 10 == index && it.type >= 10)
            }

            val listStatus : ArrayList<StatusLearn> = arrayListOf()
            listfilter.forEachIndexed { index, trafficsLearnDetail ->
                listStatus.add(StatusLearn(trafficsLearnDetail.id, 0))
            }

            viewModelScope.launch(Dispatchers.IO) {
                repository.insert(
                    TrafficsLearn(
                        trafficsLearn.id,
                        trafficsLearn.name,
                        trafficsLearn.content,
                        trafficsLearn.urlImage,
                        trafficsLearn.allLesson,
                        trafficsLearn.completeLesson,
                        listStatus
                    )
                )
            }
        }

    }

}