fn config app giphyfn GIPHY_KEY $GIPHY_KEY
fn update app giphyfn --syslog-url $SYSLOG_URL
fn config app giphyfn COMPLETER_BASE_URL "http://$DOCKER_LOCALHOST:8081"