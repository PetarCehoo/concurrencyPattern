# concurrencyPattern
design patterns that deal with the multi-threaded programming paradigm



list:
Active Object

decouples method execution from method invocation for objects that each reside in their own thread of control.
key elements of active object pattern
  1)proxy(client interface) public method provided by activeobject to client's
  2)dispatch queue-list of pending requests by client
  3)Scheduler-determine the order to execute the request
  4)callback-result to be obtained by proxy after a request is executed














