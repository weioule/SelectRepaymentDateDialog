# SelectRepaymentDateDialog
这是一个滚动选择器控件dialog

效果图：

   ![image](https://github.com/LeRothschild/SelectRepaymentDateDialog/blob/master/app/img/001.png)


使用时在showDialog（）将数据替换成自己的数据，然后再PickerView里面的返回数据的地方返回所需字段或数值。

注意当不滑动数据时点击取消和确认在setOnSelectListener回调里是拿不到数据的，因为没有触发监听。







