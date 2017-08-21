# Pre-work - To Do App

To Do App is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: Sonali Karwa

Time spent: 20 hours spent in total

## User Stories

The following **required** functionality is completed:

* [ Yes ] User can **successfully add and remove items** from the todo list
* [ Yes ] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [ Yes ] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [ Yes ] Persist the todo items [into SQLite/DB Flow](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [ Yes ] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [ No ] Add support for completion due dates for todo items (and display within listview item)
* [ Yes ] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [ No ] Add support for selecting the priority of each todo item (and display in listview item)
* [ No ] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [ Yes ] List anything else that you can get done to improve the app functionality!
1)Add App Bar menu option
2)Added a new Icon to Android Studio and used it in ListView to record copletion of Task. (Done : Green tick)

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/qBz1h5C.gif' title='Video Walkthrough' width='400' alt='Video Walkthrough' />

GIF created with [RecordIt](http://recordit.co/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** It's my first time doing App development so its been nice its similar to Web development with user interface in layouts and logic in Java classes. I have prior experience with GWT so i am familiar with widgets, buttons, text boxes from using that framework. So it was easy to design the layout for ToDo App.

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** Function of Adaptor is to generate Views for list of objects and load in listview container. It acts as a proxy between View and DataSource(Model).'convertView' is the old views that is not visible. Adaptor which uses the convertView to reuse old view instead of instantiating new View thus helps improve performance.

## Notes

Describe any challenges encountered while building the app.

1) Writing the CustomAdaptor with 2 imageviews was a challenge. One ImageView was Edit Icon..on clicking i had to pass the data at that position to the Edit Dialog Fragment...it took some time to figure the right way to do that.

2) Another challenge was the codepath documentation for DB Flow was not up to date...I faced issues with persisting the data as i was not using the right SQL commands to update /add new rows.

3) Another challenge was with the layout's ...undering the difference between wrap_content and match_parent and removing all hardcoded values so that the app works on different android phone models and versions.

## License

    Copyright [2017] [Sonali Karwa]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.