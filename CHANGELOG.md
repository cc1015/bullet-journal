- Added Controller interface with executeGame() method and 
had the BattleSalvoGameController (console controller) implement
this interface.
- Created the ProxyController class and implemented the Controller
interface.
- Implemented the executeGame() method in ProxyController to call
correct helper methods based on the incoming message from the server
that delegate to the Player methods.
- Created Json records (Coord, FleetSpec, Utils, Message, Setup, and 
Ship) so that incoming JSON messages could be processed and sent to 
server.
- Created the ShipDirection enum for the setup JSON response.
- Implemented Driver class main method to instantiate the correct 
controller based on the given command line arguments.
- Split the setupBoards into two private methods to abide by single
responsibility and to avoid excessively long methods.
  - setupBoards now only takes in user input and calls createSpecs
  to actually create the board specifications.
  - createSpecs creates the map based on given ship counts.
