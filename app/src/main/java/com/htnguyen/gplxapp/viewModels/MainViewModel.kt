package com.htnguyen.gplxapp.viewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.htnguyen.gplxapp.base.utils.parseJsonToListExam
import com.htnguyen.gplxapp.base.utils.parseJsonToListTrafficLearn
import com.htnguyen.gplxapp.base.utils.readJSONFromAsset
import com.htnguyen.gplxapp.database.*
import com.htnguyen.gplxapp.model.*
import com.htnguyen.gplxapp.repo.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TrafficLearnRepo
    private val repositoryExam: ExamRepo
    private val repositoryStatusLearn: StatusLearnRepo
    private val repositoryStatusExam: StatusExamRepo
    private val repositoryCompetition: CompetitionRepo

    var responseTrafficLearn: LiveData<List<TrafficsLearn>>
    var responseExam: LiveData<List<Exam>>

    var listAdd: ArrayList<TrafficsLearn> = arrayListOf(
        TrafficsLearn(
            0,
            "80 CÂU HỎI ĐIỂM LIỆT",
            "80 câu hỏi điểm liệt",
            "https://www.vba.vic.gov.au/__data/assets/image/0003/103971/warning-sign.png",
            80,
            0,
        ),
        TrafficsLearn(
            1,
            "KHÁI NIỆM VÀ QUY TẮC",
            "Gồm 83 câu hỏi(20 câu điểm liệt)",
            "https://banner2.cleanpng.com/20180422/qge/kisspng-computer-icons-concept-font-concepts-5add2c9c3e81e2.7797215615244443162561.jpg",
            83,
            0,
        ),
        TrafficsLearn(
            2,
            "VĂN HOÁ VÀ ĐẠO ĐỨC LÁI",
            "Gồm 5 câu hỏi",
            "https://banner2.cleanpng.com/20180706/xcx/kisspng-stock-photography-good-ethical-5b3f2b473a9c71.9277581915308665032401.jpg",
            5,
            0,
        ),
        TrafficsLearn(
            3,
            "KĨ THUẬT LÁI XE",
            "Gồm 12 câu hỏi (5 câu điểm liệt)",
            "https://png.pngtree.com/png-vector/20190130/ourlarge/pngtree-simple-green-car-cartoon-material-png-image_603239.jpg",
            12,
            0,
        ),
        TrafficsLearn(
            4,
            "BIỂN BÁO ĐƯỜNG BỘ",
            "Gồm 65 câu hỏi",
            "https://cdn.pixabay.com/photo/2011/04/14/21/05/traffic-sign-6682_960_720.png",
            65,
            0,
        ),
        TrafficsLearn(
            5,
            "SA HÌNH",
            "Gồm 35 câu hỏi",
            "https://banglaixegiare.com/wp-content/uploads/2021/07/sa-hinh-bang-b2-b1-c-2.png",
            35,
            0,
        )
    )

    var listAddExam: ArrayList<Exam> = arrayListOf(
        Exam(
            1,
            "Đề số 1",
            "25 câu/19 phút",
            0,
            0
        ),
        Exam(
            2,
            "Đề số 2",
            "25 câu/19 phút",
            0,
            0
        ),
        Exam(
            3,
            "Đề số 3",
            "25 câu/19 phút",
            0,
            0,
        ),
        Exam(
            4,
            "Đề số 4",
            "25 câu/19 phút",
            0,
            0
        ),
        Exam(
            5,
            "Đề số 5",
            "25 câu/19 phút",
            0,
            0
        ),
        Exam(
            6,
            "Đề số 6",
            "25 câu/19 phút",
            0,
            0
        ),

        Exam(
            7,
            "Đề số 7",
            "25 câu/19 phút",
            0,
            0
        ),

        Exam(
            8,
            "Đề số 8",
            "25 câu/19 phút",
            0,
            0
        )

    )

    init {
        val trafficLearnDao = TrafficLearnDatabase.getInstance(application).trafficLearnDao()
        val examDao = ExamDatabase.getInstance(application).examDao()
        val trafficStatusLearnDao = StatusLearnDatabase.getInstance(application).statusLearnDao()
        val statusExamDao = StatusExamDatabase.getInstance(application).statusExamDao()
        val competitionDao = CompetitionDatabase.getInstance(application).competitionDao()
        repository = TrafficLearnRepo(trafficLearnDao)
        repositoryExam = ExamRepo(examDao)
        repositoryStatusLearn = StatusLearnRepo(trafficStatusLearnDao)
        repositoryStatusExam = StatusExamRepo(statusExamDao)
        repositoryCompetition = CompetitionRepo(competitionDao)
        responseTrafficLearn = repository.getAll()
        responseExam = repositoryExam.getAll()
    }

    fun addData(context: Context) {
        listAdd.forEachIndexed { index, trafficsLearn ->
            viewModelScope.launch(Dispatchers.IO) {
                repository.insert(
                    TrafficsLearn(
                        trafficsLearn.id,
                        trafficsLearn.name,
                        trafficsLearn.content,
                        trafficsLearn.urlImage,
                        trafficsLearn.allLesson,
                        trafficsLearn.completeLesson,
                    )
                )
            }
        }
        listAddExam.forEachIndexed { index, exam ->
            viewModelScope.launch(Dispatchers.IO) {
                repositoryExam.insert(
                    Exam(
                        exam.id,
                        exam.name,
                        exam.content,
                        exam.time,
                        exam.completeExam
                    )
                )
            }
        }
        val json = readJSONFromAsset(context, "learn_traffic.json")
        val list = parseJsonToListTrafficLearn(json)
        val listStatus: ArrayList<StatusLearn> = arrayListOf()
        list.forEachIndexed { index, trafficsLearnDetail ->
            viewModelScope.launch(Dispatchers.IO) {
                repositoryStatusLearn.insert(
                    StatusLearn(trafficsLearnDetail.id, trafficsLearnDetail.type, 0, false)
                )
            }
        }

        val jsonExam = readJSONFromAsset(context, "exam.json")
        val listExam = parseJsonToListExam(jsonExam)
        val listStatusExam: ArrayList<StatusExam> = arrayListOf()
        listExam.forEachIndexed { index, examDetail ->
            viewModelScope.launch(Dispatchers.IO) {
                repositoryStatusExam.insert(
                    StatusExam(examDetail.id,examDetail.id_exam, examDetail.type, 0,"",examDetail.result)
                )
                repositoryCompetition.insert(
                    Competition(examDetail.id,index+1,examDetail.id_exam,examDetail.type,0,"",examDetail.result)
                )
            }
        }
    }

}