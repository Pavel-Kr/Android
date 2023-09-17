package com.example.lab1

import androidx.lifecycle.ViewModel
import com.example.lab1.ui.theme.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule

class NewsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val allNews = listOf(
        SingleNews(newsText = "Последние теплые дни. К Новосибирску приближается похолодание до +6 градусов — прогноз погоды"),
        SingleNews(newsText = "Мэрия Новосибирска назвала окончательный срок сдачи станции метро «Спортивная»."),
        SingleNews(newsText = "Переименовать Советский район и преобразовать Новосибирск в городской округ предложили в Академгородке"),
        SingleNews(newsText = "Выставка передовых технологий «Экспотехностраж-2023» прошла в Новосибирске"),
        SingleNews(newsText = "Пересмотрел «Барби»: необычного голубя розового цвета заметили новосибирцы."),
        SingleNews(newsText = "«Не смог завести, поэтому психанул»: в Новосибирске мужчина поджег свою машину."),
        SingleNews(newsText = "Фонтан «Мишка» планируют отремонтировать за 8,9 млн рублей в Новосибирске."),
        SingleNews(newsText = "Актриса из Новосибирска возмутилась долгим ремонтом у кукольного театра."),
        SingleNews(newsText = "Дурная мать: в Новосибирск едет Виктория Толстоганова — она расскажет о проблеме родителей и подростков"),
        SingleNews(newsText = "«Ну ты, Катюха, даешь!»: история сибирячки, которая рыбачит лучше любого мужика — в чем ее секрет?")
    )

    private val usedNews = mutableListOf<SingleNews>()

    private fun generateRandomNews(): SingleNews{
        val index =  (0..9).random()
        val news = allNews[index]
        if (usedNews.contains(news)){
            return generateRandomNews()
        }
        return news
    }

    private fun updateNews(){
        usedNews[_uiState.value.index] = generateRandomNews()
        updateIndex(_uiState.value.index.inc().mod(_uiState.value.news.size - 1))
    }

    private fun updateIndex(index: Int){
        _uiState.update { currentState ->
            currentState.copy(index = index)
        }
    }

    init {
        for(i in (0..4)){
            val news = generateRandomNews()
            usedNews.add(news)
        }
        _uiState.value = UiState(news = usedNews)
        val timer = Timer()
        timer.schedule(5000, 5000){
            updateNews()
        }
    }
}
