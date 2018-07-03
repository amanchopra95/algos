class WeightedQuickUnionUF :
	_parent = []
	_size = []
	_count = 0
	
	def __init__(self, n):
		self._count = n
		self._parent = [i for i in range(n)]
		self._size = [1 for _ in range(n)]

	def count(self):
		return self._count

	def find(self, p):
		self._validate(p)
		while(p != self._parent[p]):
			p = self._parent[p]
		return p

	def _validate (self, p) :

		n = len(self._parent)
		if p < 0 or p >= n :
			raise ValueError

	def connected(self, p, q) :
		return self.find(p) == self.find(q)
		
	def union(self, p, q) :
		rootP = self.find(p)
		rootQ = self.find(q)
		if (rootP == rootQ) :
			return

		if (self._size[rootP] > self._size[rootQ]) :
			self._parent[rootQ] = rootP
			self._size[rootP] += self._size[rootQ]

		else :
			self._parent[rootP] = rootQ
			self._size[rootQ] += self._size[rootP] 
		self._count -= 1


	def get(self):
		print(self._parent, self._size)




