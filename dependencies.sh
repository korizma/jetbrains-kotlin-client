rm ktor-deps.txt
coursier fetch \
  org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.8.0 \
  io.ktor:ktor-client-core-jvm:2.3.7 \
  io.ktor:ktor-client-cio-jvm:2.3.7 \
  io.ktor:ktor-http-jvm:2.3.7 \
  ch.qos.logback:logback-classic:1.4.14 \
  --classpath > ktor-deps.txt