Liskov: 
This principle lets us know when and how to inherit.
if T <-- S, if T has a property, than S should have that property conceptually.
for example: if animal speaks, then cat should speak as well. but snail cant speak, then conceptually, snail cant be animal.


we should b able to replace parent obj with child obj without breaking the way client code is using the object.
child class should extend the functionality of parent class not narrow it down.
