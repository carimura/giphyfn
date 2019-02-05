fn config app giphyfn GIPHY_KEY $GIPHY_KEY
fn update app giphyfn --syslog-url $SYSLOG_URL

# Localhost
fn config app giphyfn COMPLETER_BASE_URL "http://$DOCKER_LOCALHOST:8081"
fn config app giphyfn FN_SERVER_URL "https://carimura.ngrok.io"
fn config func giphyfn flow GETGIF_FUNC_ID "01D2GRXN83NG8G00GZJ000006N"
fn config func giphyfn flow SLACK_FUNC_ID "01D2Z53275NG8G00GZJ000001P"

# Service
# fn config app giphyfn COMPLETER_BASE_URL "http://10.96.218.105"
# fn config app giphyfn FN_SERVER_URL "http://lb.fnservice.io:90"
# fn config func giphyfn flow GETGIF_FUNC_ID "01D2J76B221BT044G0A000000S"
# fn config func giphyfn flow SLACK_FUNC_ID "01D2X26AFE1BT044G0A0000010"