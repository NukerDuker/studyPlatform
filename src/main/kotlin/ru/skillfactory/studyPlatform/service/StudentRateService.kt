package ru.skillfactory.studyPlatform.service

import lombok.Data
import org.springframework.stereotype.Service
import ru.skillfactory.studyPlatform.entity.Student
import ru.skillfactory.studyPlatform.entity.StudentRate
import ru.skillfactory.studyPlatform.repository.StudentRateRepo
import java.math.BigDecimal
import java.math.RoundingMode


@Data
@Service
class StudentRateService {
    private val studentRateRepo: StudentRateRepo? = null

    fun saveRate(courseId: Long, rate: Double, student: Student): StudentRate {
        val studentRate: StudentRate
        val rateForCourse = student.rates.stream().filter { x: StudentRate -> x.courseId == courseId }.findFirst()
        val formattedRate = this.convertDouble(rate)
        if (rateForCourse.isPresent) {
            rateForCourse.get().rate = formattedRate
            return studentRateRepo!!.save(rateForCourse.get())
        } else {
            studentRate = StudentRate.builder()
                .courseId(courseId)
                .rate(formattedRate)
                .build()
        }
        return studentRateRepo!!.save(studentRate)
    }

    private fun convertDouble(rate: Double): Double {
        var bd = BigDecimal(rate.toString())
        bd = bd.setScale(2, RoundingMode.HALF_UP)
        return bd.toDouble()
    }
}
