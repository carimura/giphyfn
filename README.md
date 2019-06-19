## Requirements
- Fn
- Flow
- Flow UI (optional but cool)
- Tools --> post-to-slack
- Google Home stuff (to doc)


## Fn
`fn start`

## Flow

```
docker run --rm \
     -p 8081:8081 \
     -e API_URL="http://$FNSERVER_IP:8080/invoke" \
     -e no_proxy=$FNSERVER_IP \
     --name flowserver \
     fnproject/flow:latest
```

## Flow UI

```
docker run -p3000:3000 -e API_URL=http://$DOCKER_LOCALHOST:8080 -e COMPLETER_BASE_URL=$COMPLETER_BASE_URL fnproject/flow:ui
```

## Set Env
1. fill out setenv.sh
2. run `./setenv.sh`

## Helidon Version

If Changes: `maven package`

To Run: `java -jar target/helidon-quickstart-mp.jar`

Ngrok for Goog Home: `ngrok http 8082 -subdomain=carimura`


## Profit

