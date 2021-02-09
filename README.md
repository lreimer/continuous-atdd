# Continuous ATDD on K8s

Continuous ATDD on K8s using the Geb Framework and Selenium Hub.

## Usage

First, we need to deploy the required Selenium infrastructure to K8s. The Selenium
hub is required, and also at least one browser node.
```bash
# deploy the Selenium hub to connect to
$ kubectl apply -f src/test/kubernetes/selenium-hub.yaml

# either deploy Chrome or Firefox or both (if you have enough memory)
$ kubectl apply -f src/test/kubernetes/selenium-node-chrome.yaml
$ kubectl apply -f src/test/kubernetes/selenium-node-firefox.yaml

# use port forwarding to Selenium Hub
$ kubectl port-forward service/selenium-hub 4444
```


## References

- https://github.com/kubernetes/examples/tree/master/staging/selenium
- https://gist.github.com/elsonrodriguez/261e746cf369a60a5e2d

## Maintainer

M.-Leander Reimer (@lreimer), <mario-leander.reimer@qaware.de>

## License

This software is provided under the MIT open source license, read the `LICENSE` file for details.
