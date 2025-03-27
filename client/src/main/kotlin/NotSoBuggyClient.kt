import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.*
import java.security.MessageDigest

fun sha256(bytes: ByteArray): ByteArray {
    val digest = MessageDigest.getInstance("SHA-256")
    return digest.digest(bytes)
}

suspend fun tryRange(client: HttpClient, url: String, startIndex: Int): ByteArray? {
    return try {
        val response = client.get(url) 
        {
            var endIndex = startIndex + 64 * 1024
            headers {
                header("Range", "bytes=$startIndex-$endIndex")
            }
        }
        if (response.status == HttpStatusCode.PartialContent) 
        {
            var responseBytes: ByteArray = response.readBytes();
            if (responseBytes.size == 0)
                null
            else
            {
                println(responseBytes.size)
                responseBytes
            }
        } 
        else 
        {
            null
        }
    } 
    catch (e: Exception) 
    {
        null
    }
}

suspend fun fetchData(url: String): ByteArray {
    HttpClient(CIO).use { client ->
        var startIndex = 0
        var message: ByteArray = byteArrayOf()
        var response: ByteArray? = tryRange(client, url, startIndex)

        while (response != null) 
        {
            message += response
            startIndex += 64 * 1024
            response = tryRange(client, url, startIndex)
        }

        return message
    }
}

suspend fun main() {
    val url = "http://127.0.0.1:8080"  
    val response = fetchData(url)
    val responseLen = response.size
    println("Resonse len: $responseLen")
}