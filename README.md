# Continuous ATDD on K8s

Continuous ATDD on K8s using the Geb Framework and Selenium Hub.

## Usage

First, we need to deploy the required Selenium infrastructure to K8s. The Selenium
hub is required, and also at least one browser node.
```bash
# deploy the Selenium hub to connect to
$ kubectl create ns selenium-hub
$ kubectl apply -f src/test/kubernetes/selenium-hub.yaml -n selenium-hub

# either deploy Chrome or Firefox or both (if you have enough memory)
$ kubectl apply -f src/test/kubernetes/selenium-node-chrome.yaml -n selenium-hub
$ kubectl apply -f src/test/kubernetes/selenium-node-firefox.yaml -n selenium-hub

# use port forwarding to Selenium Hub for local execution
$ kubectl port-forward service/selenium-hub 4444 -n selenium-hub
```

Next, we can run the Geb UI tests using either local or remote browsers.
```bash
$ ./gradlew testClasses

$ ./gradlew chromeTest          # local Chrome with UI
$ ./gradlew chromeHeadlessTest  # local Chrome in headless mode
$ ./gradlew firefoxTest         # local Firefox with UI

$ ./gradlew chromeRemote        # remote Chrome running in K8s
$ ./gradlew firefoxRemote        # remote Firefox running in K8s
```

Of course, we can also run the Geb test from within the cluster as a Cronjob or
as an adhoc pod.
```bash
$ docker build -t lreimer/continuous-atdd:latest .
$ docker push

# either run continuously using cronjob
$ kubectl apply -f src/test/kubernetes/chrome-remote-cronjob.yaml -n selenium-hub
$ kubectl apply -f src/test/kubernetes/firefox-remote-cronjob.yaml -n selenium-hub

# or run adhoc pods
$ kubectl run chrome-remote-test --image lreimer/continuous-atdd:latest --restart=Never --attach --env="SELENIUM_HUB_HOST=selenium-hub" --env="SELENIUM_HUB_PORT=4444" --command -- ./gradlew chromeRemote
$ kubectl delete pod/chrome-remote-test

$ kubectl run firefox-remote-test --image lreimer/continuous-atdd:latest --restart=Never --attach --env="SELENIUM_HUB_HOST=selenium-hub" --env="SELENIUM_HUB_PORT=4444" --command -- ./gradlew firefoxRemote
$ kubectl delete pod/firefox-remote-test
```

## References

- https://github.com/kubernetes/examples/tree/master/staging/selenium

## Maintainer

M.-Leander Reimer (@lreimer), <mario-leander.reimer@qaware.de>

## License

This software is provided under the MIT open source license, read the `LICENSE` file for details.
