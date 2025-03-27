kotlinc -classpath "$(paste -sd: ktor-deps.txt)" NotSoBuggyClient.kt -d output.jar
kotlin -classpath "$(paste -sd: ktor-deps.txt):output.jar" NotSoBuggyClientKt