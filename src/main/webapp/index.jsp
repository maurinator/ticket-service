<html ng-app="tickets">
    <head>
      <title>Ticket Reservation</title>
      <link rel="stylesheet" type="text/css" href="/assets/stylesheets/style.css"/>
    </head>
    <body ng-controller="main" style="display: flex;">
      <div style="width: 30%; display: flex; align-items: center; justify-content: center;">
        <div style="padding: 20px; border-radius: 4px; background: rgba(255, 255, 255, 1);">
            <input placeholder="Type your email..." style="padding: 10px; border-radius: 4px; border: none;">
            <div ng-click="reserveSeat();" style="border-radius: 4px; cursor: pointer; text-align: center; margin-top: 10px; padding: 10px; background: rgba(81, 95, 144, 1); color: rgb(255, 255, 255);">
                Reserve
            </div>
        </div>

      </div>
      <div style="width: 70%; display: flex; align-items: center; flex-direction: column; padding: 20px;">
        <div style="margin-bottom: 20px; border-radius: 4px; width: 100%; height: 80px; background: steelblue; display: flex; align-items: center; justify-content: center;"> Stage </div>
        <div ng-repeat="(i, row) in rows" style="display: flex; margin-top: 10px; margin-bottom: 10px;">
          <div ng-click="selectSeat(row, column);" ng-repeat="(j , column) in columns" ng-style="{ 'background': seatsSelected[i] && seatsSelected[i].indexOf(j + 1) > -1 ? 'rgba(81, 95, 144, 1)': '#fff' }" style="cursor: pointer; border-radius: 4px; display: flex; align-items: center; justify-content: center; width: 40px; height: 40px; margin-left: 10px; margin-right: 10px;">
            {{row + '-' + column}}
          </div>
        <div>
      </div>
      <script src="/assets/lib/angular.min.js"></script>
      <script src="/assets/javascript/app.js"></script>
    </body>
</html>
