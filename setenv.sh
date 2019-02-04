fn config app giphyfn GIPHY_KEY $GIPHY_KEY
fn update app giphyfn --syslog-url $SYSLOG_URL
fn config app giphyfn COMPLETER_BASE_URL "http://$DOCKER_LOCALHOST:8081"
fn config app giphyfn FN_SERVER_URL "https://carimura.ngrok.io"
fn config func giphyfn flow GETGIF_FUNC_ID "01D2GRXN83NG8G00GZJ000006N"
fn config func giphyfn flow SLACK_FUNC_ID "01D2N6WGASNG8G00GZJ00000KR"