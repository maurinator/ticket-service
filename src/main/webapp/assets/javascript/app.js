var app = angular.module('tickets', []);
app.controller("main", ["$scope", "$http", function ($scope, $http) {
  console.log('$scope', $scope);
  $http.get('/stage').then(function (response) {
    console.log('response', response);
    var data = response.data.stage;
    console.log('data', data);

    var arr = data.split('],');
    for (var i = 0; i < arr.length - 1; i++) {
        arr[i] = eval(arr[i].concat("]"));
    }
    arr[arr.length - 1] = eval(arr[arr.length - 1]);

    console.log(arr);
    $scope.arr = arr;

    $scope.rows = [];
    for (var i = 0; i < arr.length; i++) {
      $scope.rows.push(String.fromCharCode(65 + i));
    }
    $scope.columns = [];
    for (var i = 0; i < arr[0].length; i++) {
      $scope.columns.push(i + 1);
    }
  });

  angular.extend($scope, {
    seatsSelected: {},
    selectSeat: function (row, column) {
      console.log('selectSeat', row, column);
      var r = row.charCodeAt(0) - 65;
      console.log(r);
      console.log($scope.arr[r][column]);
      var d = [r, column];
      if ($scope.seatsSelected[r]) {
        if ($scope.seatsSelected[r].indexOf(column) > -1) {
           console.log('if exists, remove');
           $scope.seatsSelected[r].splice($scope.seatsSelected[r].indexOf(column), 1);
        } else {
           console.log('row exists, column does not');
           $scope.seatsSelected[r].push(column);
        }
      } else {
        console.log('row does not exist');
        $scope.seatsSelected[r] = [];
        $scope.seatsSelected[r].push(column);
      }
    },
    reserveSeat: function () {
        console.log('reserveSeat');
        $http.post('/reserve', {
            seats: $scope.seatsSelected
        }).then(function (response) {
            console.log(response);
        });
    }
  })
}]);
